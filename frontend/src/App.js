import React, { useState } from 'react';
import { useBackendUrl } from './hooks/useBackendUrl';

function App() {
  const backendUrl = useBackendUrl();
  const [prompt, setPrompt] = useState('');
  const [response, setResponse] = useState('');

  const handleAsk = async () => {
    try {
      const res = await fetch(`${backendUrl}/chat/ask?prompt=${encodeURIComponent(prompt)}`);
      const text = await res.text();
      setResponse(text);
    } catch (error) {
      setResponse('Error fetching response');
    }
  };

  return (
    <div style={{ padding: '20px', backgroundColor: 'black', color: 'white' }}>
      <h1>Chat</h1>
      <input
        type="text"
        value={prompt}
        onChange={(e) => setPrompt(e.target.value)}
        placeholder="Enter your prompt"
      />
      <button onClick={handleAsk}>Ask</button>
      <p>Response: {response}</p>
    </div>
  );
}

export default App;
