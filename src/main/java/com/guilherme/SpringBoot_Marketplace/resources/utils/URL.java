package com.guilherme.SpringBoot_Marketplace.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
    // Classe auxiliar para quebrar a URL e inserir a lista procurada pelo usuário

    public static String decodeParam (String s) {
        try {
            URLDecoder.decode(s,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
        return "";
    }


    public static List<Integer> decodeIntList(String s) {

        String[] vet = s.split(",");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < vet.length; i++) {
            list.add(Integer.parseInt(vet[i]));
        }
        return list;

        //Utilizando lambda
        //  return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }

}
