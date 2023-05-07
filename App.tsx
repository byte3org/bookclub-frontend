import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';

import AppContainer from './src/appContainer';
import { AuthProvider } from "./src/authContext";

export default function App() {
  return (
    <AuthProvider>
        <AppContainer/>
    </AuthProvider>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
