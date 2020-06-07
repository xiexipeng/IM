package im;

public interface Command {

	Byte LOGIN_REQUEST = 1;

	Byte LOGIN_RESPONSE = 2;

	Byte MESSAGE_REQUEST = 3;

	Byte MESSAGE_RESPONSE = 4;

	Byte GROUP_CREATE_REQUEST = 5;

	Byte GROUP_CREATE_RESPONSE = 6;

	Byte JOIN_GROUP_REQUEST = 7;

	Byte JOIN_GROUP_RESPONSE = 8;
}
