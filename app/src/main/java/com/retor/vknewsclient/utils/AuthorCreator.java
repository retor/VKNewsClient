package com.retor.vknewsclient.utils;

import com.retor.vklib.model.Group;
import com.retor.vklib.model.Profile;

import java.util.List;

/**
 * Created by retor on 10.09.2015.
 */
public class AuthorCreator {

    public static Author getAuthor(List<Profile> profiles, List<Group> groups, final int id) {
        Author out = null;
        if (id > 0) {
            for (Profile prof : profiles) {
                if (prof.getId() == id)
                    out = new Author(prof.getFirst_name() + " " + prof.getLast_name(), prof.getPhoto_100(), id);
            }
        } else {
            for (Group group : groups) {
                if (group.getId() == (id * (-1)))
                    out = new Author(group.getName() + " " + group.getScreen_name(), group.getPhoto_100(), id);
            }
        }
        return out;
    }

    public static class Author {
        private String name;
        private String pic;
        private int id;

        public Author(final String name, final String pic, final int id) {
            this.name = name;
            this.pic = pic;
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPic() {
            return pic;
        }
    }
}
