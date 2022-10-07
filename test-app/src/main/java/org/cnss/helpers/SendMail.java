package org.cnss.helpers;

import models.SendEnhancedRequestBody;
import models.SendEnhancedResponseBody;
import models.SendRequestMessage;
import services.Courier;
import services.SendService;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class SendMail {
    public SendMail() {
    }

    public String sendMail(String email) {
        Courier.init("pk_prod_Q02ESMDGP3MRBNMWTZ3Q1XN0A244");
        SendEnhancedRequestBody sendEnhancedRequestBody = new SendEnhancedRequestBody();
        SendRequestMessage sendRequestMessage = new SendRequestMessage();
        System.out.println(email);
        HashMap<String, String> to = new HashMap<String, String>();
        to.put("email", email);
        sendRequestMessage.setTo(to);

        HashMap<String, String> content = new HashMap<String, String>();
        content.put("title", "CLe d'entrer : Nouveau Cle");
        int idOne = Math.abs(UUID.randomUUID().hashCode());
        content.put("body", "Code :" + idOne);
        sendRequestMessage.setContent(content);

        HashMap<String, Object> data = new HashMap<String, Object>();
        sendRequestMessage.setData(data);
        sendEnhancedRequestBody.setMessage(sendRequestMessage);

        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(sendEnhancedRequestBody);
            System.out.println("nous somme envoyer le cle d'entrer dans votre email || entrer votre cle d'entrer || or taper exit pour sortie :");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(idOne);
    }

    public Boolean sendMessage(String email, String message){
        Courier.init("pk_prod_Q02ESMDGP3MRBNMWTZ3Q1XN0A244");
        SendEnhancedRequestBody sendEnhancedRequestBody = new SendEnhancedRequestBody();
        SendRequestMessage sendRequestMessage = new SendRequestMessage();
        System.out.println(email);
        HashMap<String, String> to = new HashMap<String, String>();
        to.put("email", email);
        sendRequestMessage.setTo(to);

        HashMap<String, String> content = new HashMap<String, String>();
        content.put("title", "Answer for your application");
        int idOne = Math.abs(UUID.randomUUID().hashCode());
        content.put("body", message);
        sendRequestMessage.setContent(content);

        HashMap<String, Object> data = new HashMap<String, Object>();
        sendRequestMessage.setData(data);
        sendEnhancedRequestBody.setMessage(sendRequestMessage);

        try {
            SendEnhancedResponseBody response = new SendService().sendEnhancedMessage(sendEnhancedRequestBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}

