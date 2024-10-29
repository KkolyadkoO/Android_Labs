package com.example.lab7;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainFragment extends Fragment  {
    private TextInputEditText wordInput;
    private FloatingActionButton searchButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        wordInput = view.findViewById(R.id.word_input);
        searchButton = view.findViewById(R.id.fab_search);

        searchButton.setOnClickListener(v -> searchWord());

        return view;
    }

    private void searchWord() {
        String word = wordInput.getText().toString().trim();
        if (word.isEmpty()) {
            Toast.makeText(getContext(), "Введите слово для поиска", Toast.LENGTH_SHORT).show();
            return;
        }

        int frequency = countWordOccurrences(word);
        Snackbar.make(requireView(), "Слово \"" + word + "\" встречается " + frequency + " раз(а)", Snackbar.LENGTH_LONG).show();
    }

    private int countWordOccurrences(String word) {
        int count = 0;
        try {
            // Открываем файл и читаем его содержимое
            InputStream inputStream = requireContext().openFileInput("textfile.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String w : words) {
                    if (w.equalsIgnoreCase(word)) {
                        count++;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            createTextFile();
            Toast.makeText(getContext(), "Ошибка при чтении файла", Toast.LENGTH_SHORT).show();
        }
        return count;
    }
    private void createTextFile() {
        String sampleText = "In a small town nestled between rolling hills and dense forests, there lived a curious child named Alice. "
                + "Alice was not like other children; she had a boundless curiosity and a thirst for knowledge that knew no limits. "
                + "Every day after school, instead of playing with other children, she would run to the town’s library to read books about the world beyond her small town. "
                + "She read about ancient civilizations, scientific discoveries, and mysteries of the universe. Her favorite subject, however, was the ocean. "
                + "Alice was captivated by the tales of explorers who ventured into the unknown depths of the sea, discovering strange creatures and underwater landscapes "
                + "that looked like scenes from a dream.\n\n"
                + "One rainy afternoon, as Alice was reading an old book about marine biology, she came across a story that sparked her imagination. "
                + "It was a tale of an ancient city hidden deep beneath the waves, a city that, according to legend, held treasures beyond imagination. "
                + "But it was not the treasures that fascinated Alice; it was the thought of the forgotten knowledge and secrets buried within the city’s walls. "
                + "Determined to learn more, she spent weeks researching everything she could find about underwater ruins, ancient myths, and deep-sea exploration.\n\n"
                + "Her parents noticed her growing obsession and were both worried and proud. They knew that Alice was no ordinary child. "
                + "She was destined for something special, something beyond the simple life of their small town. "
                + "They encouraged her curiosity but reminded her to stay grounded and remember the importance of balance in life. "
                + "Alice, however, was unstoppable. She continued to read and dream, imagining herself as an explorer, uncovering secrets hidden from the world for centuries.\n\n"
                + "Years passed, and Alice’s dream grew stronger with time. She studied hard, excelled in her studies, and eventually earned a scholarship to a prestigious university "
                + "where she could study marine biology and archaeology. She was one step closer to fulfilling her dream of uncovering the mysteries of the deep sea. "
                + "Her journey was only beginning, but she knew that she was on the path to something extraordinary. "
                + "And in her heart, she carried the dream of finding the lost city, the city that had inspired her as a child, "
                + "the city that had ignited her passion and fueled her lifelong quest for knowledge.";

        try {
            FileOutputStream outputStream = requireContext().openFileOutput("textfile.txt", Context.MODE_PRIVATE);
            outputStream.write(sampleText.getBytes());
            outputStream.close();
            Toast.makeText(getContext(), "Файл успешно создан", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getContext(), "Ошибка при создании файла", Toast.LENGTH_SHORT).show();
        }
    }
}
