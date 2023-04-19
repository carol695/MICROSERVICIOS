package org.acme.services;

import org.acme.model.Stream;
import org.acme.model.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface StreamService {

    List<Stream> getAllStreams();


    void createStream(Stream stream);

    Stream getStreamById(ObjectId id);

    Stream updateStream(String id, Stream stream);

    void deleteStream(String id);
}
