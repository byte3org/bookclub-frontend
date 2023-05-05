import React, { createContext, useState, useEffect, useContext } from "react";
import storage from './utils/storage';

type AuthContextData = {
    authToken?: string;
    isLoaded: boolean;
    signIn(token: string): Promise<void>;
    signOut(): void;
};

const AuthContext = React.createContext<AuthContextData>({} as AuthContextData);

const AuthProvider: React.FC = ({ children }) => {
    const [authToken, setAuthToken] = useState<string>();

    const [isLoaded, setLoaded] = useState(false);

    useEffect(() => {
        storage.getUserSecureSetting('token')
            .then(t => {
                if (t) {
                    //console.log("authtoken found t : ", t);
                    setAuthToken(t);
                    setLoaded(true);
                } else {
                    console.log("authtoken empty");
                    setLoaded(true)
                }
            }).catch(err => {
                console.log('error : ', err);
            })
    }, []);

    /* make these to async if fails */
    const signIn = async (token: string) => {
        storage.setUserSecureSetting(["token"], [JSON.stringify(token)])
            .then(() => {
                setAuthToken(token);
            })
    };

    const signOut = async () => {
        storage.deleteUserSecureSetting("token").then(() => {
            setAuthToken(undefined);
        })
    };

    return (
        <AuthContext.Provider value={{ authToken: authToken, isLoaded: isLoaded, signIn, signOut }}>
            {children}
        </AuthContext.Provider>
    );
};

function useAuth(): AuthContextData {
    const context = useContext(AuthContext);

    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }

    return context;
}

export { AuthContext, AuthProvider, useAuth };