package com.engagement.controller;

import com.engagement.model.Admin;
import com.engagement.model.Client;
import com.engagement.model.Message;
import com.engagement.model.dto.MessageAdminDTO;
import com.engagement.model.dto.MessageClientDTO;
import com.engagement.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MessageController {

  private MessageService messageService;
  private AdminController adminController;


  
  @Autowired
  public MessageController (MessageService messageService){
    super();
    this.messageService = messageService;
  }

  @GetMapping("/msg")
  public List<Message> findAll(){
    return messageService.getMessages();
  }

  @GetMapping("/msg/{messageId}")
  public Message getMessageById(@PathVariable int messageId){
    return messageService.getMessageById(messageId);
  }

  @PostMapping("/msg/admin")
  public Message addMessageAdmin(@RequestBody MessageAdminDTO messageAdminDTO){
    return messageService.addMessageAdmin(messageAdminDTO);
  }

  @PostMapping("/msg/client")
  public Message addMessageClient(@RequestBody MessageClientDTO messageClientDTO){
	  
    return messageService.addMessageClient(messageClientDTO);
  }


  @DeleteMapping("/msg/{messageId}")
  public String deleteMessage(@PathVariable int messageId){
    return messageService.deleteMessage(messageId);
  }



}
