import { useNavigation } from "@react-navigation/native";
import React from "react";
import { Box, Checkbox, Button, Center, Text, Heading, HStack, Input, KeyboardAvoidingView, VStack, FormControl } from "native-base";

export default function UserInfo() {
    const navigation = useNavigation();

    const [contact, setContact] = React.useState('');
    const [houseName, setHouseName] = React.useState('');
    const [streetName, setStreetName] = React.useState('');
    const [city, setCity] = React.useState('');
    const [province, setProvince] = React.useState('');

    const onClickConfirm = () => {

    }

    const onClickSkip = () => {

    }

    return (
        <Center flex={1} _light={{ bg: 'blueGray.50' }} >
            <Box safeArea p="10" py="8" w="100%">
                <KeyboardAvoidingView behavior="position">
                    <VStack space={4}>
                        <Heading size="lg">
                            User Information
                        </Heading>
                        <FormControl>
                            <FormControl.Label>Contact</FormControl.Label>
                            <Input variant="underlined" onChangeText={(text) => { setContact(text); }} />
                        </FormControl>
                        <FormControl>
                            <FormControl.Label>Address</FormControl.Label>
                            <Input variant="underlined" onChangeText={(text) => { setHouseName(text); }} />
                            <Input variant="underlined" onChangeText={(text) => { setStreetName(text); }} />
                            <Input variant="underlined" onChangeText={(text) => { setCity(text); }} />
                            <Input variant="underlined" onChangeText={(text) => { setProvince(text); }} />
                        </FormControl>
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
        </Center>
    )
}