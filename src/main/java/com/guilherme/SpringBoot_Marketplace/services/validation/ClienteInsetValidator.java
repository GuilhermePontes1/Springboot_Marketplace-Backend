package com.guilherme.SpringBoot_Marketplace.services.validation;

import com.guilherme.SpringBoot_Marketplace.CategoriaDTO.ClienteNewDTO;
import com.guilherme.SpringBoot_Marketplace.domain.enums.TipoCliente;
import com.guilherme.SpringBoot_Marketplace.resources.exception.FieldMessage;
import com.guilherme.SpringBoot_Marketplace.services.validation.utils.BR;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsetValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Override
    public void initialize(ClienteInsert ann) {

    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();
        // inserindo erros na lista

        if (objDto.getTipo().equals(TipoCliente.PESSSOA_FISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
                list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }
        if (objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
