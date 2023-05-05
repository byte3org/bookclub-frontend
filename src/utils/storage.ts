import AsyncStorage from "@react-native-async-storage/async-storage";
import * as SecureStore from 'expo-secure-store';

export async function getUserSetting(key: string, shouldParse: boolean = true) {
    let result = await AsyncStorage.getItem(key);
    if (result) {
        if (shouldParse) {
            return JSON.parse(result);
        }
        return result;
    }
}

export async function setUserSettings(keys: string[], values: string[]) {
    if (keys.length != values.length) {
        console.log('number of keys and values does not match');
    }
    try {
        for (let i = 0; i < keys.length; i++) {
            await AsyncStorage.setItem(keys[i], values[i]);
        }
    } catch (err) {
        console.log('error : ', err);
    }
}

export async function getUserSecureSetting(key: string, shouldParse: boolean = false) {
    let result = await SecureStore.getItemAsync(key);
    if (result) {
        if (shouldParse) {
            return JSON.parse(result);
        }
        return result;
    }
}

export async function setUserSecureSetting(keys: string[], values: string[]) {
    if (keys.length != values.length) {
        console.log('number of keys and values does not match')
    }
    try {
        for (let i = 0; i < keys.length; i++) {
            await SecureStore.setItemAsync(keys[i], values[i]);
        }
    } catch (err) {
        console.log('error : ', err);
    }
}

export async function deleteUserSecureSetting(key: string) {
    return await SecureStore.deleteItemAsync(key);
}

export default { setUserSettings, getUserSetting, getUserSecureSetting, setUserSecureSetting, deleteUserSecureSetting };
