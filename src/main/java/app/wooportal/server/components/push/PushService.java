package app.wooportal.server.components.push;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Message.Builder;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.WebpushConfig;
import com.google.firebase.messaging.WebpushNotification;
import app.wooportal.server.components.subscription.base.SubscriptionEntity;
import app.wooportal.server.components.subscription.base.SubscriptionService;

@Service
public class PushService {

  private final SubscriptionService subscriptionService;


  public PushService(SubscriptionService subscriptionService, PushConfig config)
      throws IOException {
    this.subscriptionService = subscriptionService;
    initializePushService(config);
  }

  private void initializePushService(PushConfig config) throws IOException {
    ClassPathResource firebaseConfigFile = new ClassPathResource(config.getCredentials());

    if (firebaseConfigFile.exists() && FirebaseApp.getApps().isEmpty()) {
      FirebaseApp.initializeApp(FirebaseOptions.builder()
          .setCredentials(GoogleCredentials.fromStream(firebaseConfigFile.getInputStream()))
          .build());
    }
  }

  public void sendNotification(MessageDto message) {
    for (var subscription : subscriptionService.getAllSubscriptions()) {
      sendPush(subscription, message, new HashMap<String, String>());
    }
  }

  public void sendPush(SubscriptionEntity subscription, MessageDto message,
      Map<String, String> additionalData) {
    try {
      var messageBuilder =
          Message.builder().setToken(subscription.getDeviceToken()).setNotification(Notification
              .builder().setTitle(message.getTitle()).setBody(message.getContent()).build());

      if (additionalData != null) {
        messageBuilder.setAndroidConfig(AndroidConfig.builder().putAllData(additionalData).build())
            .setApnsConfig(ApnsConfig.builder()
                .setAps(Aps.builder().putAllCustomData(new HashMap<String, Object>(additionalData))
                    .build())
                .build())
            .setWebpushConfig(WebpushConfig.builder()
                .setNotification(WebpushNotification.builder().setData(additionalData).build())
                .build());
      }
      FirebaseMessaging.getInstance().sendAsync(messageBuilder.build()).get();

    } catch (InterruptedException | ExecutionException e) {
      subscriptionService.deleteById(subscription.getId());
    }

  }
}
