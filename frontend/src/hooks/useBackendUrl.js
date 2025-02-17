import { useState, useEffect } from 'react';

export const useBackendUrl = (defaultUrl = '') => {
  const [backendUrl, setBackendUrl] = useState(defaultUrl);

  useEffect(() => {
    fetch('/.env')
      .then(res => res.text())
      .then(text => {
        const match = text.match(/^BACKEND_URL=(.*)$/m);
        if (match && match[1]) {
          setBackendUrl(match[1].trim());
          console.log("Loaded BACKEND_URL from .env:", match[1].trim());
        }
      })
      .catch(err => console.error("Error reading .env file:", err));
  }, []);

  return backendUrl;
};
