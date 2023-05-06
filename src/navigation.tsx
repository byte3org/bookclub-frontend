import React from "react";
import { createNativeStackNavigator } from '@react-navigation/native-stack';

const Stack = createNativeStackNavigator();



export function AuthNavigation() {
    return (
        <Stack.Navigator screenOptions={{ headerShown: false }} >

        </Stack.Navigator>
    )
}

export function AppNavigation() {
    return (
        <Stack.Navigator screenOptions={{ headerShown: false }} >
        </Stack.Navigator>
    )
}

export default { AppNavigation, AuthNavigation }