package com.app.repository.impl;

import com.app.repository.QuestWithAnswerRepository;
import com.app.repository.model.QuestWithAnswer;
import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implementation of the QuestWithAnswerRepository interface for managing quests with their associated answers.
 * This class provides methods to retrieve quests along with their answers from the database.
 */
@Repository
@RequiredArgsConstructor
public class QuestWithAnswerRepositoryImpl implements QuestWithAnswerRepository {
    private final Jdbi jdbi;

    /**
     * Retrieves a quest with its answers based on the provided quest ID.
     *
     * @param id The ID of the quest to retrieve.
     * @return A list of QuestWithAnswer objects associated with the specified quest ID.
     */
    public List<QuestWithAnswer> findQuestWithAnswer(Long id) {
        var sql = """
                select q.id, q.difficulty_level, q.category, q.question, a.answer, a.confirm
                from quests q join answers a on q.id = a.quest_id where q.id = :id;""";

        return jdbi.withHandle(handle ->
                handle
                        .createQuery(sql)
                        .bind("id", id)
                        .mapToBean(QuestWithAnswer.class)
                        .list());
    }

    /**
     * Retrieves all quests along with their answers.
     *
     * @return A list of all QuestWithAnswer objects in the database.
     */
    public List<QuestWithAnswer> findQuestsWithAnswers() {
        var sql = """
                select q.id, q.difficulty_level, q.category, q.question, a.answer, a.confirm
                from quests q join answers a on q.id = a.quest_id""";

        return jdbi.withHandle(handle ->
                handle
                        .createQuery(sql)
                        .mapToBean(QuestWithAnswer.class)
                        .list());
    }

    /**
     * Retrieves quests with their answers based on a list of quest IDs.
     *
     * @param ids A list of quest IDs to retrieve.
     * @return A list of QuestWithAnswer objects associated with the specified quest IDs.
     */
    public List<QuestWithAnswer> findQuestsWithAnswersByIds(List<Long> ids) {
        var sql = """
                select q.id, q.difficulty_level, q.category, q.question, a.answer, a.confirm
                from quests q join answers a on q.id = a.quest_id where q.id in (<ids>)""";

        var items = jdbi.withHandle(handle ->
                handle.createQuery(sql)
                        .bindList("ids", ids)
                        .mapToBean(QuestWithAnswer.class)
                        .list());

        return items;
    }
}
