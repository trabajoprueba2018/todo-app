package com.dorefactor.todoapp.domain;

public class Task {

    private final long id;
    private final String name;
    private final String description;

    public Task(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder {

        private long id;
        private String name;
        private String description;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Task build() {
            return new Task(id, name, description);
        }
    }
    
    public static Builder builder() {
        return new Builder();
    }

}
