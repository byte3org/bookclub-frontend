import React from "react";
import { useNavigation } from "@react-navigation/native";
import { useAuth } from "../authContext";
import { Image, Box, Button, Center, Text, Heading, HStack, Input, KeyboardAvoidingView, VStack, FormControl } from "native-base";

import { api } from "../api/api";

export default function SignIn() {
    const navigation = useNavigation();
    const auth = useAuth();
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');

    const [success, setSuccess] = React.useState(false);
    const [error, setError] = React.useState(false);
    const [msg, setMsg] = React.useState('');

    const onClickSignUp = () => {
        navigation.navigate('signup');
    }

    const onClickSignIn = (e: any) => {
        e.preventDefault();
        if (username == '' || password == '') {
            setError(true);
            setMsg("Username or password is empty");
        } else {
            api.post(
                "/user/login",
                {
                    username: username,
                    password: password
                }
            ).then(res => {
                if (res.status === 200) {
                    const token = res.data.token;
                    auth.signIn(token).then(() => {
                        setSuccess(true);
                        setMsg('authentication successful');
                    }).catch(err => {
                        console.log('error : ', err);
                        setError(true);
                        setMsg(err);
                    })
                } else {
                    setError(true);
                    setMsg('error occured when connecting to the server');
                }
            }).catch(err => {
                console.log(err);
                setMsg(err);
                setError(true);
            })
        }
    }

    return (
        <Center flex={1} _light={{ bg: 'blueGray.50' }} >
            <Box safeArea p="10" py="8" w="100%">
                <KeyboardAvoidingView behavior="position">
                    <VStack space={4}>
                        <Image size={150} borderRadius={100} src={"../../assets/icon.png"} alt="BookClub Logo" />
                        <Heading size="lg">
                            Sign In
                        </Heading>
                        <Text >
                            Hi there! Nice to see you again.
                        </Text>
                        <FormControl>
                            <FormControl.Label>Email</FormControl.Label>
                            <Input variant="underlined" onChangeText={(text) => { setUsername(text); }} />
                        </FormControl>
                        <FormControl>
                            <FormControl.Label>Password</FormControl.Label>
                            <Input variant="underlined" type="password" onChangeText={(text) => { setPassword(text); }} />
                        </FormControl>
                        <Button rounded={'md'} mt="2" colorScheme="green" onPress={onClickSignIn}>
                            Sign in
                        </Button>
                        <Text alignSelf={'center'}>
                            or use one of your social profiles
                        </Text>
                        <Button rounded={'md'} mt="2" colorScheme="blue" onPress={onClickSignIn}>
                            Google
                        </Button>
                        <HStack>
                            <Text fontSize="sm" color="blueGray.900" _dark={{ color: "blueGray.200" }}>
                                Create new account{" "}
                            </Text>
                            <Button ml={130} p={0} variant={'ghost'} colorScheme={'green'} onPress={onClickSignUp}>Sign Up</Button>
                        </HStack>
                    </VStack>
                </KeyboardAvoidingView>
            </Box>
        </Center >
    )
}
