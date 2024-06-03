import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import '../styles/globals.css'
import Navbar from "@/components/Navbar";
import {ClerkProvider} from "@clerk/nextjs";
import {QueryClient} from "@tanstack/query-core";
import {QueryClientProvider} from "@tanstack/react-query";
import Providers from "@/components/Providers";

const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
  title: 'Ureddit',
  description: 'A Reddit clone built with Next.js and Java',
}
const queryClient = new QueryClient()

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
        <ClerkProvider >
          <html lang="en" className={inter.className}>

                      <body className='min-h-screen pt-10 bg-slate-50 '>
                      <Providers>
                        {/*providers 放错位置会导致 渲染错误 */}
                        <Navbar/>
                        <div  className="container mx-auto pt-12 h-full">{children}</div>
                      </Providers>
                      </body>

          </html>
          </ClerkProvider >
  )
}
