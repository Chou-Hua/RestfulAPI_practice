package com.andy.springtest.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service // 讓Spring 知道他是一個服務類別，需要綁定
public class FirebaseInitializer {
    @PostConstruct //Annotation 指定函數ininalize() 創建firebase連接
    private  void initDB() throws IOException {
       InputStream serviceAccount = this.getClass().getClassLoader().getResourceAsStream("./spring-boot-10798-firebase-adminsdk-mndss-bbacb1907e.json");//讀取我們的key json
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://spring-boot-10798.firebaseio.com")
                .build();

        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }
    public Firestore getFirebase(){
        return FirestoreClient.getFirestore();
    }
}
