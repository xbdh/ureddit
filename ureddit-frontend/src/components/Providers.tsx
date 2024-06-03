'use client'
import React, {FC} from 'react';
import {QueryClientProvider} from "@tanstack/react-query";
import {QueryClient} from "@tanstack/query-core";

const queryClient = new QueryClient()

interface ProvidersProps {
    children: React.ReactNode;
}

const Providers :FC<ProvidersProps>= ({children}) => {
    return (
        <div>
             <QueryClientProvider client={queryClient}>
                {children}
            </QueryClientProvider>
        </div>
    );
};

export default Providers;