import React from "react";
import { useAuth } from "../authContext";

export default function SignIn() {
    const auth = useAuth();
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');

    const [success, setSuccess] = React.useState(false);
    const [error, setError] = React.useState(false);
    const [msg, setMsg] = React.useState('');

    const onClickSignup = () => {
        // navigation to sign up
    }

    const onClickLogin = (e: any) => {
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
        
    )
}