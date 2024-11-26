package com.example.lab14;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PlayerAdapter adapter;
    private List<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        players = createPlayerList();
        adapter = new PlayerAdapter(players);
        recyclerView.setAdapter(adapter);

        ImageLoaderThread imageLoaderThread = new ImageLoaderThread(players, adapter);
        imageLoaderThread.start();
    }

    private List<Player> createPlayerList() {
        return Arrays.asList(
                new Player(1, "Криштиану Роналду", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/Cristiano_Ronaldo_playing_for_Al_Nassr_FC_against_Persepolis%2C_September_2023_%28cropped%29.jpg/250px-Cristiano_Ronaldo_playing_for_Al_Nassr_FC_against_Persepolis%2C_September_2023_%28cropped%29.jpg"),
                new Player(2, "Лионель Месси", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b4/Lionel-Messi-Argentina-2022-FIFA-World-Cup_%28cropped%29.jpg/250px-Lionel-Messi-Argentina-2022-FIFA-World-Cup_%28cropped%29.jpg"),
                new Player(3, "Луис Суарес", "https://s-cdn.sportbox.ru/images/styles/upload/fp_fotos/98/1b/f81294fde5726a25fe914b9fcba97fc85e7785479297f142360402.jpg"),
                new Player(4, "Антуан Гризманн", "https://img.championat.com/news/big/b/y/antuan-grizmann-prokommentiroval-pobedu-atletiko-nad-pszh-v-lige-chempionov_1730977506130566430.jpg"),
                new Player(5, "Неймар", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/20180610_FIFA_Friendly_Match_Austria_vs._Brazil_Neymar_850_1705.jpg/233px-20180610_FIFA_Friendly_Match_Austria_vs._Brazil_Neymar_850_1705.jpg"),
                new Player(6, "Гарет Бэйл", "https://img.championat.com/news/big/k/x/garet-bejl-obyasnil-pochemu-zavershil-kareru-v-vozraste-33-let_1688943495818519325.jpg"),
                new Player(7, "Роберт Левандовский", "https://s-cdn.sportbox.ru/images/styles/1200-auto/fp_fotos/74/39/47253e13dc1c49d60bac2da44ed41b4563472dfe4489f495260913.jpg"),
                new Player(8, "Кевин де Брюйне", "https://sport5.by/upload/iblock/0eb/d0356dkmg9ger698afcnmmh1ocx89c2n.jpg"),
                new Player(9, "Серхио Агуэро", "https://upload.wikimedia.org/wikipedia/commons/7/7b/Ag%C3%BCero_in_2018.jpg"),
                new Player(10, "Мануэль Нойер", "https://upload.wikimedia.org/wikipedia/commons/1/10/20180602_FIFA_Friendly_Match_Austria_vs._Germany_Manuel_Neuer_850_0723.jpg"),
                new Player(11, "Томас Мюллер", "https://img.championat.com/news/big/k/w/video-tomas-myuller-pytaetsya-popast-myachom-po-kryshe-stadiona-pered-matchem-evro-2024_17195155112041403232.jpg"),
                new Player(12, "Лука Модрич", "https://upload.wikimedia.org/wikipedia/commons/c/c8/ISL-HRV_%287%29_%28cropped%29_%28cropped%29.jpg"),
                new Player(13, "Поль Погба", "https://s0.rbk.ru/v6_top_pics/media/img/4/69/347280641587694.jpeg"),
                new Player(14, "Пьер-Эмерик Обамейанг", "https://upload.wikimedia.org/wikipedia/commons/3/37/1_Pierre-Emerick_Aubameyang_%28cropped%29.jpg"),
                new Player(15, "Гонсало Игуаин", "https://s-cdn.sportbox.ru/images/styles/1200-auto/fp_fotos/ee/6e/260f465945bba6ff9298b1acb0516ad360855f44795c6809558432.jpg")
        );
    }
}
