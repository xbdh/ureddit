"use client";

import EditorJS from "@editorjs/editorjs";
import Header from "@editorjs/header";
import List from "@editorjs/list";
import Table from "@editorjs/table";
import Code from "@editorjs/code";
import { usePathname, useRouter } from "next/navigation";
import { useCallback, useEffect, useRef, useState } from "react";
import { useForm } from "react-hook-form";
import TextareaAutosize from "react-textarea-autosize";

import { useMutation } from "@tanstack/react-query";
import axios from "axios";

import "@/styles/editor.css";
import { type } from "os";

interface EditorProps {
    subredditId: string;
}

type FormData = {
    title: string;
};
type PostCreationRequest = {
    title: string;
    content: any;
    subredditId: string;
};

export const Editor: React.FC<EditorProps> = ({ subredditId }) => {
    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm<FormData>({
        defaultValues: {
            title: "",
        },
    });
    const ref = useRef<EditorJS>();
    const [isMounted, setIsMounted] = useState(false);
    const router = useRouter();
    const pathname = usePathname();
    //const _titleRef = useRef<HTMLTextAreaElement>();

    const { mutate: createPost } = useMutation({
        mutationFn: async ({
            title,
            content,
            subredditId,
        }: PostCreationRequest) => {
            const payload: PostCreationRequest = {
                title,
                content,
                subredditId,
            };
            const { data } = await axios.post(
                "/api/subreddit/post/create",
                payload
            );
            return data;
        },
        onError: () => {
            return "fuck";
        },
        onSuccess: () => {
            // turn pathname /r/mycommunity/submit into /r/mycommunity
            const newPathname = pathname.split("/").slice(0, -1).join("/");
            router.push(newPathname);

            router.refresh();

            return "fuck";
        },
    });

    const initEditor = useCallback(() => {
        console.log("fuck");
        if (!ref.current) {
            const editor = new EditorJS({
                holder: "editor",
                onReady() {
                  ref.current = editor;
                },
                placeholder: "Type here to write your post...",
                inlineToolbar: true,
                data: { blocks: [] },
                tools: {
                    header: Header,

                    list: List,
                    code: Code,
                    table: Table,
                },
            });
        }
    }, []);
    useEffect(() => {
        setIsMounted(true);
    }, []);

    useEffect(() => {
        //_titleRef.current?.focus()

        if (isMounted) {//一定要判断，否则会重复渲染，出现多次Type here to write your post...//原因可能跟strict mode有关
            initEditor();
        }

        return () => {
            ref.current?.destroy;
            ref.current = undefined;
        };
    }, [isMounted, initEditor]);

    // useEffect(() => {
    //   if (Object.keys(errors).length) {
    //     for (const [_key, value] of Object.entries(errors)) {
    //       value;
    //       // toast({
    //       //     title: 'Something went wrong.',
    //       //     description: (value as { message: string }).message,
    //       //     variant: 'destructive',
    //       // })
    //     }
    //   }
    // }, [errors]);

    // useEffect(() => {
    //   // if (typeof window !== "undefined") {

    //   // }
    //   setIsMounted(true);

    // }, []);

    // useEffect(() => {
    //   const init = async () => {
    //     await initializeEditor();

    //     // setTimeout(() => {
    //     //   _titleRef?.current?.focus();
    //     // }, 0);
    //   };

    //   if (isMounted) {
    //     initializeEditor();

    //     return () => {
    //       ref.current?.destroy();
    //       ref.current = undefined;
    //     };
    //   }
    // }, [isMounted]);

    async function onSubmit(data: FormData) {
        const blocks = await ref.current?.save();

        const payload: PostCreationRequest = {
            title: data.title,
            content: blocks,
            subredditId,
        };

        createPost(payload);
    }

    // if (!isMounted) {
    //   return null;
    // }

    //const { ref: titleRef, ...rest } = register("title");

    return (
        <div className="w-full p-4 bg-zinc-50 rounded-lg border border-zinc-200">
            <form
                id="subreddit-post-form"
                className="w-fit"
                onSubmit={handleSubmit(onSubmit)}
            >
                <div className="prose prose-stone dark:prose-invert">
                    <TextareaAutosize
                       
                        //   ref={(e) => {
                        //     titleRef(e);
                        //     _titleRef.current = e;
                        //   }}
                        // {...rest}
                        {...register("title", { required: true })}
                        placeholder="Title"
                        className="w-full resize-none appearance-none overflow-hidden bg-transparent text-5xl font-bold focus:outline-none"
                    />
                    <div id="editor" className="min-h-[500px]" />
                    <p className="text-sm text-gray-500">
                        Use{" "}
                        <kbd className="rounded-md border bg-muted px-1 text-xs uppercase">
                            Tab
                        </kbd>{" "}
                        to open the command menu.
                    </p>
                </div>
            </form>
        </div>
    );
};
