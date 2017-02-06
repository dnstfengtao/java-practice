package com.finley.protobuffers;

import org.junit.Test;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @author fengjiantao.
 * @since 12/29/16.
 */
public class PersonMessageTest {

    @Test
    public void testPersonMessage() {
        PersonMessage.Person.Builder builder = PersonMessage.Person.newBuilder();
        builder.setEmail("fengjiantao@hotmail.com");
        builder.setId(1);
        builder.setName("TestName");

        builder.addPhone(PersonMessage.Person.PhoneNumber.newBuilder().setNumber("131111111").setType(PersonMessage
                .Person.PhoneType.MOBILE));
        builder.addPhone(PersonMessage.Person.PhoneNumber.newBuilder().setNumber("011111").setType(PersonMessage
                .Person.PhoneType.HOME));


        PersonMessage.Person person = builder.build();
        byte[] buf = person.toByteArray();

        try {
            PersonMessage.Person person2 = PersonMessage.Person.parseFrom(buf);

            System.out.println(person2);

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

    }

}
