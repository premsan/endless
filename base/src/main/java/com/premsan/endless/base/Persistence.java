package com.premsan.endless.base;

import java.util.List;

public interface Persistence {

    void saveWrite(Write write);

    List<Write> nextWrites(String lastId, int limit);


}
