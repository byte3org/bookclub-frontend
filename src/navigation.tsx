import React from "react";
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import SignIn from "./screens/signIn";
import SignUp from "./screens/signUp";

const Stack = createNativeStackNavigator();

export function AuthNavigation() {
    return (
        <Stack.Navigator screenOptions={{ headerShown: false }} >
            <Stack.Screen name={"signin"} component={SignIn}></Stack.Screen>
            <Stack.Screen name={"signup"} component={SignUp}></Stack.Screen>
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