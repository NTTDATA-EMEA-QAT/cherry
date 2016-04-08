package io.mcore;

public interface Mission<RESULT> {

    RESULT accomplishAs(Agent agent);
}
