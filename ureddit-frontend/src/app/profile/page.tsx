import React from 'react';
import {SignIn, UserProfile} from "@clerk/nextjs";

const Page = () => {
    return (
        <div className='container '>
            <div className='flex flex-col items-center justify-center gap-20'>
                <UserProfile/>
            </div>
        </div>
    );
};

export default Page;