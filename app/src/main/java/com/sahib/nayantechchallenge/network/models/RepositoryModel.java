package com.sahib.nayantechchallenge.network.models;

import java.io.Serializable;
import java.util.List;

public class RepositoryModel implements Serializable {
    private List<Repo> items;

    public List<Repo> getItems() {
        return items;
    }

    public void setItems(List<Repo> items) {
        this.items = items;
    }

    public class Repo implements Serializable{
        private String name;
        private String description;
        private String language;
        private int watchers_count;
        private String html_url;
        private OwnerModel owner;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public int getWatchers_count() {
            return watchers_count;
        }

        public void setWatchers_count(int watchers_count) {
            this.watchers_count = watchers_count;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public OwnerModel getOwner() {
            return owner;
        }

        public void setOwner(OwnerModel owner) {
            this.owner = owner;
        }
    }
}
