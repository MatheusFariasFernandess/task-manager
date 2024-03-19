package org.tcc.api.service;

import org.springframework.stereotype.Service;
import org.tcc.api.DTO.input.AnexoDTO;
import org.tcc.api.enums.FileFormat;

import java.io.*;
import java.util.Base64;

@Service
public class AnexoService {
    private final static String PATH = "C:\\Users\\Matheus\\Documents\\taskmanager\\api\\src\\main\\resources\\imagens\\";

    protected AnexoDTO salvarArquivo(AnexoDTO anexoDTO){
        if(anexoDTO.getNome()!=null  && anexoDTO.getArquivo()!=null ) {
            byte[] arquivo = Base64.getDecoder().decode(anexoDTO.getArquivo());
            anexoDTO.setNome(PATH + anexoDTO.getNome() + FileFormat.JPG.getDescricaoFormato());

            try (OutputStream outputStream = new FileOutputStream(anexoDTO.getNome())) {
                outputStream.write(arquivo);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return anexoDTO;
    }
}
