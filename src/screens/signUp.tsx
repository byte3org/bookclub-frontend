import React from "react";
import { useNavigation, useRoute } from "@react-navigation/native";
import { Box, Checkbox, Button, Center, Text, Heading, HStack, Input, KeyboardAvoidingView, VStack, FormControl } from "native-base";

import { api } from "../api/api";

export default function SignUp() {
    const navigation = useNavigation();

    const [username, setUsername] = React.useState('');
    const [email, setEmail] = React.useState('');
    const [password, setPassword] = React.useState('');

    const [isAgreed, setAgreed] = React.useState(false);

    const [success, setSuccess] = React.useState(false);
    const [error, setError] = React.useState(false);
    const [msg, setMsg] = React.useState('');

    const onClickSignIn = () => {
        navigation.navigate('signin');
    }

    const onClickSignUp = () => {
        if (isAgreed) {
            api.post(
                "user/signup",
                {
                    username: username,
                    password: password,
                    email: email
                }
            ).then(res => {
                if (res.status === 201) {
                    console.log('user created');
                    setSuccess(true);
                    setMsg('User created');
                    //let sleep = () => { new Promise(resolve => setTimeout(resolve, 10)); }
                    navigation.navigate('signin');
                } else {
                    console.log('error occured when registering new user');
                    setMsg("Failed to register new accounr");
                    setError(true);
                }
            }).catch(err => {
                console.log(err);
                setMsg(err);
                setError(true);
            })
        } else {
            setMsg("Please agree to terms and conditions");
        }
    }

    return (

        <Center flex={1} _light={{ bg: 'blueGray.50' }} >
            <Box safeArea p="10" py="8" w="100%">
                <KeyboardAvoidingView behavior="position">
                    <VStack space={4}>
                        <Heading size="lg">
                            Sign Up
                        </Heading> 
                        <FormControl>
                            <FormControl.Label>Email</FormControl.Label>
                            <Input variant="underlined" onChangeText={(text) => { setUsername(text); }} />
                        </FormControl>
                        <FormControl>
                            <FormControl.Label>Username</FormControl.Label>
                            <Input variant="underlined" onChangeText={(text) => { setPassword(text); }} />
                        </FormControl>
                        <FormControl>
                            <FormControl.Label>Password</FormControl.Label>
                            <Input variant="underlined" type="password" onChangeText={(text) => { setPassword(text); }} />
                        </FormControl>
                        <Checkbox onChange={(value) => { setAgreed(value); }} size="sm" value="one" my={2}>
                            I agree to the Terms of Services and Privacy Policy.
                        </Checkbox>
                        <Button rounded={'md'} mt="2" colorScheme="green" onPress={onClickSignUp}>
                            Continue
                        </Button>
                        <HStack>
                            <Text fontSize="sm" color="blueGray.900" _dark={{ color: "blueGray.200" }}>
                                Have an account? {" "}
                            </Text>
                            <Button ml={130} p={0} variant={'ghost'} colorScheme={'green'} onPress={onClickSignIn}>Sign In</Button>
                        </HStack>
                    </VStack>
                </KeyboardAvoidingView>
            </Box>
        </Center >
    )
}
