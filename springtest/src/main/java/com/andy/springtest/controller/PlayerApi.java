package com.andy.springtest.controller;

import com.andy.springtest.Model.PlayerInfo;
import com.andy.springtest.service.PlayerService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.andy.springtest.service.FirebaseInitializer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


@RestController
public class PlayerApi {
    @Autowired()
    FirebaseInitializer db;

    @GetMapping("/getAllPlayer")  //查詢
    public List<PlayerInfo> getPlayer() throws ExecutionException, InterruptedException {
        List<PlayerInfo> plList = new ArrayList<PlayerInfo>();
        CollectionReference player = db.getFirebase().collection("Player");
        ApiFuture<QuerySnapshot> querySnapshot = player.get();
        for(DocumentSnapshot doc:querySnapshot.get().getDocuments()){
            PlayerInfo pla = doc.toObject(PlayerInfo.class);
            plList.add(pla);
        }
        return plList;
    }
    @PostMapping("/savePlayer") //新增一名Player 建立
    public int  savePlayer(@RequestBody PlayerInfo player ) {
        CollectionReference playerCR = db.getFirebase().collection("Player");
        playerCR.document("Player1").set(player); //創一個firebase 的文件名稱
        return player.getAccount();
    }
    @PutMapping("/updatePlayer1") //更新文件 = 指定一個玩家，欄位全更新 這邊指定Player1
    public int updatePlayer1(@RequestBody PlayerInfo player  ) throws InterruptedException, ExecutionException {
        CollectionReference playeraccount = db.getFirebase().collection("Player");
        ApiFuture<WriteResult> writeResult =playeraccount.document("Player1").set(player);
        return  1;
    }
    @PutMapping("/updatePlayer1Account") //更新文件欄位 = 指定一個文件內的欄位更新這邊指定Player1
    public int  updatPlayer1Account(@RequestBody PlayerInfo player){
        CollectionReference playeraccount = db.getFirebase().collection("Player");
        ApiFuture<WriteResult> writeResult =playeraccount.document("Player1").update("account",500);
        return  1;
    }
    @DeleteMapping("/deletePlayer1Account") //刪除文件欄位 = 指定一個文件內的欄位刪除
    public int deletePlayer1Account(@RequestBody  PlayerInfo player){
        CollectionReference playeraccount = db.getFirebase().collection("Player");
        ApiFuture<WriteResult> writeResult =playeraccount.document("Player1").update("account", FieldValue.delete());
        return 1;
    }
    @DeleteMapping("/deletePlayer1") //刪除文件欄位 = 指定一個文件內的欄位刪除
    public int deletePlayer1(@RequestBody  PlayerInfo player){
        CollectionReference playeraccount = db.getFirebase().collection("Player");
        ApiFuture<WriteResult> writeResult =playeraccount.document("Player1").delete();
        return 1;
    }
/*
    @PostMapping("/getPlayer")
    public PlayerInfo getPlayer(@RequestParam("id") int id) {
        return new PlayerInfo();
    }
*/
}
/*
    @Autowired(required = false)
    PatService patService;
    @Autowired(required = false)
    PlayerInfo playerInfo;

    @GetMapping("/getname")
  /*  public String getName(@RequestBody String name ) throws InterruptedException, ExecutionException {
    };*/
/*
    @RequestMapping("/get/Player")
    public PlayerInfo platertest(){
        PlayerInfo playerInfo = new PlayerInfo();
        playerInfo.setAccount(1000);
        playerInfo.setId(666);
        playerInfo.setName("cindy");
        return  playerInfo;
    }
}
*/
/*
@RestController
public class PlayerApi {
    @Autowired(required=false)
    PlayerInfo playerInfo;
        @RequestMapping("/get/Player")
        public PlayerInfo platertest(){
            PlayerInfo playerInfo = new PlayerInfo();
            playerInfo.setAccount(1000);
            playerInfo.setId(666);
            playerInfo.setName("cindy");
            return  playerInfo;
        }

}
*/