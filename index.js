import { NativeModules } from 'react-native';
let DocumentScanner = NativeModules.DocumentScanner;
if (!DocumentScanner) {
    throw Error("DocumentScanner native module was not loaded.");
}

module.exports = DocumentScanner;