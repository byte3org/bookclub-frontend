import React from "react";
import { useNavigation, useRoute } from "@react-navigation/native";

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
        <></>
    )
}