package io.magentys;

public interface Mission<RESULT> {

    RESULT accomplishAs(Agent agent);
}
