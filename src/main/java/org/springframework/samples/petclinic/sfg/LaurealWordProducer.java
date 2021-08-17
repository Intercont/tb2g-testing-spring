package org.springframework.samples.petclinic.sfg;

import org.springframework.stereotype.Component;

@Component
public class LaurealWordProducer implements WordProducer{
    @Override
    public String getWord() {
        return "Laureal";
    }
}
