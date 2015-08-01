package com.twu.biblioteca.enumeration;

public enum MainMenu {
    LIST_BOOKS("List Books");

    private String prompt;

    MainMenu(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

}
