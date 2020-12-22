package xyz.euclia.jaqpotj.models;

/**
 *
 * @author Pantelispanka
 *
 */
public class EntryId {

        String URI;
        String name;
        String ownerUUID;
        String type;

        public EntryId(){}
        public EntryId(String URI, String name, String ownerUUID) {
            this.URI = URI;
            this.name = name;
            this.ownerUUID = ownerUUID;
        }

        public String getURI() {
            return URI;
        }

        public void setURI(String URI) {
            this.URI = URI;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOwnerUUID() {
            return ownerUUID;
        }

        public void setOwnerUUID(String ownerUUID) {
            this.ownerUUID = ownerUUID;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "EntryId{" + "URI=" + URI + '}';
        }

    }
