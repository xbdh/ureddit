import React from 'react';
import Link from "next/link";

import {DiApple} from "react-icons/di";
import {auth, currentUser, UserButton} from "@clerk/nextjs";

const Navbar = () => {
    const {userId} = auth()
    // const data = await  currentUser ()
    // console.log("------------------")
    // console.log(data)
    return (
        <div className='fixed  inset-0 h-fit bg-zinc-100 border-b  border-zinc-300 z-[10] py-2'>
            <div className='container mx-auto flex items-center justify-between gap-2'>

                <Link href='/' className='flex gap-2 items-center'>
                    <DiApple color='blue' className='w-8 h-8 sm:h-6 sm:w-6 '/>
                    <p className='hidden text-zinc-700 text-sm font-medium md:block'>
                        Ureddit</p>
                </Link>

                <div className='flex gap-6 items-center'>
                    {!userId &&(
                        <>
                            <Link href='/sign-in' >Sign In</Link>
                            <Link href='/sign-up' >Sign up</Link>
                        </>)}

                    {/* {userId&&(
                            <Link href='/r/create' >Create Community</Link>)}

                    {userId&&(
                            <Link href='/profile' >Profile</Link>)} */}

                    <div>
                        <UserButton afterSignOutUrl='/' />
                    </div>

                </div>
            </div>
        </div>
            );
};

export default Navbar;