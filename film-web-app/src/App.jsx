
import { ConfigProvider, Layout, theme } from 'antd';
import Header from './Components/Header';
import LandingPage from './Pages/LandingPage';
import { useState } from 'react';

function App() {
  const [format, setFormat] = useState("application/json");
  const [search, setSearch] = useState("");
  return (
    <>
    <ConfigProvider
        theme={{
          algorithm: theme.defaultAlgorithm,
          token: {
            colorPrimary: '#cdb4db', // Lavender
            colorBgBase: '#fffafc',  // very light background
            colorTextBase: '#333',
            fontFamily: 'Poppins, sans-serif',
            }
        }}
      >
        <Layout>
          <Header format={format} setFormat={setFormat} setSearch={setSearch} />
          <LandingPage format={format} search={search} />
        </Layout>
      </ConfigProvider>
    </>
  )
}

export default App
