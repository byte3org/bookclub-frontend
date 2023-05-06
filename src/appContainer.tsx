import { NavigationContainer } from '@react-navigation/native';
import { Center, Heading, NativeBaseProvider } from "native-base";

import { AuthNavigation, AppNavigation } from './navigation';
//import theme from './theme';
import { useAuth } from './authContext';


export default function AppContainer() {
    const { authToken, isLoaded } = useAuth();

    if (!isLoaded) {
        return <NativeBaseProvider theme={theme}><Center flex={1}><Heading>Loading BookClub</Heading></Center></NativeBaseProvider>
    }
    return (
        <NavigationContainer>
            <NativeBaseProvider theme={theme}>
                {authToken ? <AppNavigation /> : <AuthNavigation />}
            </NativeBaseProvider>
        </NavigationContainer>
    );
};