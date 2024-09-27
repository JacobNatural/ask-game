package com.app.repository.impl;

import com.app.extension.repository.QuestRepositoryExtension;
import com.app.extension.txt.load.QuestLoadExtension;
import com.app.txt.load.impl.QuestFileReader;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.util.List;
import static com.app.data_provider.DataProvider.*;

@ExtendWith({QuestRepositoryExtension.class, QuestLoadExtension.class})
@RequiredArgsConstructor
public class QuestRepositoryImplGetQuestsTest {
    private final QuestRepositoryImpl questRepository;
    private final QuestFileReader questLoad;

    @Test
    @DisplayName("When the quests is retrieved from the repository")
    public void test1(){
        Assertions.assertThat(questRepository.getQuests())
                .contains(QUEST2, QUEST3, QUEST1);
    }

    @Test
    @DisplayName("When the repository reads data from an empty text file")
    public void test2(){
        var questRepository2 = new QuestRepositoryImpl(getPath(FILENAME_EMPTY_FILE),questLoad);

        Assertions.assertThat(questRepository2.getQuests())
                .isEqualTo(List.of());
    }
}
