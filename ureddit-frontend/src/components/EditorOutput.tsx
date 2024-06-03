'use client'


import { FC } from 'react'
// import Output from 'editorjs-react-renderer'
import {ParagraphOutput} from 'editorjs-react-renderer'

import dynamic from 'next/dynamic'   //这个是个坑，要用dynamic，不能用import 否则会报错，一直加载不出来

const Output = dynamic(
  async () => (await import('editorjs-react-renderer')).default,
  { ssr: false }
)

interface EditorOutputProps {
  content: any
}

// const renderers = {
//   image: CustomImageRenderer,
//   code: CustomCodeRenderer,
// }

const style = {
  paragraph: {
    fontSize: '0.875rem',
    lineHeight: '1.25rem',
  },
}

const EditorOutput: FC<EditorOutputProps> = ({ content }) => {
    
 //console.log("content",content)
 // 将content转化为object
  const  data=JSON.parse(content) //这是个坑，content是string，要转化为object
  return (
    // <ParagraphOutput data={ content.paragraph } style={ style.paragraph } />
    <Output
      style={style}
      className='text-sm'
    //   renderers={renderers}
      data={data}
    />
  )
}

export default EditorOutput
