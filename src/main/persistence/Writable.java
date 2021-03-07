package persistence;

import org.json.JSONObject;

/*
Represents a writable object
Citation: code obtained from Writable class in JsonSerializationDemo
 */
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
