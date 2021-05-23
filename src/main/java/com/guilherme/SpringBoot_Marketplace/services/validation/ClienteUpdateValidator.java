package com.guilherme.SpringBoot_Marketplace.services.validation;

import com.guilherme.SpringBoot_Marketplace.CategoriaDTO.ClienteDTO;
import com.guilherme.SpringBoot_Marketplace.domain.Cliente;
import com.guilherme.SpringBoot_Marketplace.repositories.ClienteRepository;
import com.guilherme.SpringBoot_Marketplace.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private HttpServletRequest request; // permite obter o parametro da URI

    @Override
    public void initialize(ClienteUpdate ann) {

    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {


        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id")); /* para fazer a validação do metodo de uptade do email é necessárrio utilizar desta lógica,
                                                          Basicamente o que ela faz é o seguinte: Captura o URL do json para pegar nesse caso o objeto cliente e o cliente
                                                          especificado que irá mudar o valor da variavel email, para poder efeturar o uptade do email é necessário verificar
                                                          se aquele email já existe dentro do banco de dados*/

        List<FieldMessage> list = new ArrayList<>();
        // inserindo erros na lista

        Cliente aux = repo.findByEmail(objDto.getEmail());
        if (aux != null &&  !aux.getId().equals(uriId)) {
                list.add(new FieldMessage("email", "E-mail já existente "));
            }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
