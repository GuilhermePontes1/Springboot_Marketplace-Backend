package com.guilherme.SpringBoot_Marketplace.services;

import com.guilherme.SpringBoot_Marketplace.services.exception.FileException;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageService {

    public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) { // para pegar uma BufferedImage e converter para JPG
        String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename()); // pegamos a extensão do arquivo
        if (!"png".equals(ext) && !"jpg".equals(ext)) { // testamos se a extensão é JPG OU PNG, se não for rejeitamos
            throw new FileException("Somente imagens PNG e JPG são permitidas");
        }

        try {
            BufferedImage img = ImageIO.read(uploadedFile.getInputStream()); // tentamos obter a BufferedImage
            if ("png".equals(ext)) { // testa se ela é PNG, se for png converte para JPG
                img = pngToJpg(img);
            }
            return img;
        } catch (IOException e) {
            throw new FileException("Erro ao ler arquivo");
        }
    }

    public BufferedImage pngToJpg(BufferedImage img) {   //boilerplate , OBS: o branco é pq algumas imagens PNG tem fundo "Vazio" então o branco preenche
        BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
        return jpgImage;
    }

    public InputStream getInputStream(BufferedImage img, String extension) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(img, extension, os);
            return new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            throw new FileException("Erro ao ler arquivo");
        }
    }
    public BufferedImage cropSquare(BufferedImage sourceImg) { // serve para fazer o recorte da imagem
        int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth(); // descobre qual o tamanho  minimo
        return Scalr.crop( // então ele recorta
                sourceImg,
                (sourceImg.getWidth()/2) - (min/2),
                (sourceImg.getHeight()/2) - (min/2),
                min,
                min);
    }

    public BufferedImage resize(BufferedImage sourceImg, int size) { // aqui ela é redimensionada e colocada na qualidade maxima
        return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
    }


}





