package com.simplifiedBanking.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.simplifiedBanking.domain.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {
  
  // private final RestTemplate restTemplate;

  @Value("${send.email.url}")
  private String sendEmailUrl;

  public void sendNotification(User user, String message) throws Exception {
    // String email = user.getEmail();
    // NotificationRequest notificationRequest = new NotificationRequest(email, message);

    // ResponseEntity<String> notificationResponse = restTemplate.postForEntity(sendEmailUrl, notificationRequest, String.class);

    // if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
    //   throw new Exception("The notification service is down");
    // }

    System.out.println(message);
  }
}
