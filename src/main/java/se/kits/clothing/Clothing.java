package se.kits.clothing;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = Clothing.Builder.class)
public class Clothing {
    public final String name;
    public final String color;


    public Clothing(String name, String color) {
        this.name = name;
        this.color = color;
    }


    @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "with")
    public static class Builder {
        private String name;
        private String color;

        public Builder() {
        }


        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public Clothing build() {
            return new Clothing(name, color);
        }

    }
}
