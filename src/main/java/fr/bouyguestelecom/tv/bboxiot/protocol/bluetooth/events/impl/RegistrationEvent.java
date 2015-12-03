package fr.bouyguestelecom.tv.bboxiot.protocol.bluetooth.events.impl;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

import fr.bouyguestelecom.tv.bboxiot.protocol.bluetooth.events.enums.EventRegistration;
import fr.bouyguestelecom.tv.bboxiot.protocol.bluetooth.events.enums.EventTopic;
import fr.bouyguestelecom.tv.bboxiot.protocol.bluetooth.events.enums.EventType;
import fr.bouyguestelecom.tv.bboxiot.protocol.bluetooth.events.GenericEventAbstr;
import fr.bouyguestelecom.tv.bboxiot.protocol.bluetooth.events.constant.Common;
import fr.bouyguestelecom.tv.bboxiot.protocol.bluetooth.events.inter.IRegistrationEvent;

/**
 * @author Bertrand Martel
 */
public class RegistrationEvent extends GenericEventAbstr implements IRegistrationEvent {

    private static String TAG = RegistrationEvent.class.getSimpleName();

    private Set<EventRegistration> registrationTypeList = new HashSet<>();

    public RegistrationEvent(EventTopic topic, EventType type, String eventId, JSONObject data) {
        super(topic, type, eventId, data);

        try {
            if (data.has(Common.CONSTANT_COMMON_DATA)) {

                JSONArray array = (JSONArray) data.getJSONArray(Common.CONSTANT_COMMON_DATA);

                for (int i = 0; i < array.length(); i++) {
                    registrationTypeList.add(EventRegistration.getRegistration(array.getInt(i)));
                }
            } else {
                Log.e(TAG, "Error in Bluetooth registration event format");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<EventRegistration> getRegistrationType() {
        return registrationTypeList;
    }
}