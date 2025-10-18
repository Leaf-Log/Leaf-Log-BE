package com.example.leaflog.bc.til.note.application.port.out;

import java.util.List;

public interface GithubNotePort {
    void createNote(String title, String content, String githubName, String repoName, String accessToken);

    void updateNote(String title, String content, String githubName, String repoName, String accessToken);

    void deleteNote(String title, String githubName, String repoName, String accessToken);

    void verifyNoteExists(String title, String githubName, String repoName, String accessToken);

    String queryNote(String title, String githubName, String repoName, String accessToken);

    List<String> queryAllNotes(String githubName, String repoName, String accessToken);
}
