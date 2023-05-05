import React from "react";
import { Button } from "native-base";

type Props = {
    children: React.ReactNode
    size: string
    onPress: (params: any) => any
}

const PrimaryButton = (props: Props) => {
    return (
        <Button height={ } size={props.size} colorScheme={'green'} onPress={props.onPress} >
            {props.children}
        </Button>
    )
}

export { PrimaryButton };