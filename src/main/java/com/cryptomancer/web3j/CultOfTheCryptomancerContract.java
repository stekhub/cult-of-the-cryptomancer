package com.cryptomancer.web3j;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.2.
 */
@SuppressWarnings("rawtypes")
public class CultOfTheCryptomancerContract extends Contract {
    public static final String BINARY = "[\r\n"
            + "\t{\r\n"
            + "\t\t\"anonymous\": false,\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"indexed\": true,\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"owner\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t},\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"indexed\": true,\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"spender\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t},\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"indexed\": false,\r\n"
            + "\t\t\t\t\"internalType\": \"uint256\",\r\n"
            + "\t\t\t\t\"name\": \"value\",\r\n"
            + "\t\t\t\t\"type\": \"uint256\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"Approval\",\r\n"
            + "\t\t\"type\": \"event\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"anonymous\": false,\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"indexed\": true,\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"previousOwner\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t},\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"indexed\": true,\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"newOwner\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"OwnershipTransferred\",\r\n"
            + "\t\t\"type\": \"event\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"anonymous\": false,\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"indexed\": true,\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"from\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t},\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"indexed\": true,\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"to\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t},\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"indexed\": false,\r\n"
            + "\t\t\t\t\"internalType\": \"uint256\",\r\n"
            + "\t\t\t\t\"name\": \"value\",\r\n"
            + "\t\t\t\t\"type\": \"uint256\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"Transfer\",\r\n"
            + "\t\t\"type\": \"event\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"account\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"addAdept\",\r\n"
            + "\t\t\"outputs\": [],\r\n"
            + "\t\t\"stateMutability\": \"nonpayable\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"account\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t},\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"uint256\",\r\n"
            + "\t\t\t\t\"name\": \"amount\",\r\n"
            + "\t\t\t\t\"type\": \"uint256\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"addAndFundCultist\",\r\n"
            + "\t\t\"outputs\": [],\r\n"
            + "\t\t\"stateMutability\": \"nonpayable\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"account\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"addCultist\",\r\n"
            + "\t\t\"outputs\": [],\r\n"
            + "\t\t\"stateMutability\": \"nonpayable\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"owner\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t},\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"spender\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"allowance\",\r\n"
            + "\t\t\"outputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"uint256\",\r\n"
            + "\t\t\t\t\"name\": \"\",\r\n"
            + "\t\t\t\t\"type\": \"uint256\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"stateMutability\": \"view\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"spender\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t},\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"uint256\",\r\n"
            + "\t\t\t\t\"name\": \"amount\",\r\n"
            + "\t\t\t\t\"type\": \"uint256\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"approve\",\r\n"
            + "\t\t\"outputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"bool\",\r\n"
            + "\t\t\t\t\"name\": \"\",\r\n"
            + "\t\t\t\t\"type\": \"bool\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"stateMutability\": \"nonpayable\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"account\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"balanceOf\",\r\n"
            + "\t\t\"outputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"uint256\",\r\n"
            + "\t\t\t\t\"name\": \"\",\r\n"
            + "\t\t\t\t\"type\": \"uint256\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"stateMutability\": \"view\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [],\r\n"
            + "\t\t\"name\": \"decimals\",\r\n"
            + "\t\t\"outputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"uint8\",\r\n"
            + "\t\t\t\t\"name\": \"\",\r\n"
            + "\t\t\t\t\"type\": \"uint8\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"stateMutability\": \"view\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"spender\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t},\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"uint256\",\r\n"
            + "\t\t\t\t\"name\": \"subtractedValue\",\r\n"
            + "\t\t\t\t\"type\": \"uint256\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"decreaseAllowance\",\r\n"
            + "\t\t\"outputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"bool\",\r\n"
            + "\t\t\t\t\"name\": \"\",\r\n"
            + "\t\t\t\t\"type\": \"bool\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"stateMutability\": \"nonpayable\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [],\r\n"
            + "\t\t\"name\": \"destroy\",\r\n"
            + "\t\t\"outputs\": [],\r\n"
            + "\t\t\"stateMutability\": \"nonpayable\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"account\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"getCreationTimestampOfAdept\",\r\n"
            + "\t\t\"outputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"uint256\",\r\n"
            + "\t\t\t\t\"name\": \"\",\r\n"
            + "\t\t\t\t\"type\": \"uint256\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"stateMutability\": \"view\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"account\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"getCreationTimestampOfCultist\",\r\n"
            + "\t\t\"outputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"uint256\",\r\n"
            + "\t\t\t\t\"name\": \"\",\r\n"
            + "\t\t\t\t\"type\": \"uint256\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"stateMutability\": \"view\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"spender\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t},\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"uint256\",\r\n"
            + "\t\t\t\t\"name\": \"addedValue\",\r\n"
            + "\t\t\t\t\"type\": \"uint256\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"increaseAllowance\",\r\n"
            + "\t\t\"outputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"bool\",\r\n"
            + "\t\t\t\t\"name\": \"\",\r\n"
            + "\t\t\t\t\"type\": \"bool\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"stateMutability\": \"nonpayable\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"account\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t},\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"uint256\",\r\n"
            + "\t\t\t\t\"name\": \"amount\",\r\n"
            + "\t\t\t\t\"type\": \"uint256\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"mint\",\r\n"
            + "\t\t\"outputs\": [],\r\n"
            + "\t\t\"stateMutability\": \"nonpayable\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [],\r\n"
            + "\t\t\"name\": \"name\",\r\n"
            + "\t\t\"outputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"string\",\r\n"
            + "\t\t\t\t\"name\": \"\",\r\n"
            + "\t\t\t\t\"type\": \"string\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"stateMutability\": \"view\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [],\r\n"
            + "\t\t\"name\": \"owner\",\r\n"
            + "\t\t\"outputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"stateMutability\": \"view\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"account\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"removeAdept\",\r\n"
            + "\t\t\"outputs\": [],\r\n"
            + "\t\t\"stateMutability\": \"nonpayable\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"account\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"removeCultist\",\r\n"
            + "\t\t\"outputs\": [],\r\n"
            + "\t\t\"stateMutability\": \"nonpayable\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [],\r\n"
            + "\t\t\"name\": \"renounceOwnership\",\r\n"
            + "\t\t\"outputs\": [],\r\n"
            + "\t\t\"stateMutability\": \"nonpayable\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [],\r\n"
            + "\t\t\"name\": \"symbol\",\r\n"
            + "\t\t\"outputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"string\",\r\n"
            + "\t\t\t\t\"name\": \"\",\r\n"
            + "\t\t\t\t\"type\": \"string\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"stateMutability\": \"view\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [],\r\n"
            + "\t\t\"name\": \"totalSupply\",\r\n"
            + "\t\t\"outputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"uint256\",\r\n"
            + "\t\t\t\t\"name\": \"\",\r\n"
            + "\t\t\t\t\"type\": \"uint256\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"stateMutability\": \"view\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"to\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t},\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"uint256\",\r\n"
            + "\t\t\t\t\"name\": \"amount\",\r\n"
            + "\t\t\t\t\"type\": \"uint256\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"transfer\",\r\n"
            + "\t\t\"outputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"bool\",\r\n"
            + "\t\t\t\t\"name\": \"\",\r\n"
            + "\t\t\t\t\"type\": \"bool\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"stateMutability\": \"nonpayable\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"from\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t},\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"to\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t},\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"uint256\",\r\n"
            + "\t\t\t\t\"name\": \"amount\",\r\n"
            + "\t\t\t\t\"type\": \"uint256\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"transferFrom\",\r\n"
            + "\t\t\"outputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"bool\",\r\n"
            + "\t\t\t\t\"name\": \"\",\r\n"
            + "\t\t\t\t\"type\": \"bool\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"stateMutability\": \"nonpayable\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t},\r\n"
            + "\t{\r\n"
            + "\t\t\"inputs\": [\r\n"
            + "\t\t\t{\r\n"
            + "\t\t\t\t\"internalType\": \"address\",\r\n"
            + "\t\t\t\t\"name\": \"newOwner\",\r\n"
            + "\t\t\t\t\"type\": \"address\"\r\n"
            + "\t\t\t}\r\n"
            + "\t\t],\r\n"
            + "\t\t\"name\": \"transferOwnership\",\r\n"
            + "\t\t\"outputs\": [],\r\n"
            + "\t\t\"stateMutability\": \"nonpayable\",\r\n"
            + "\t\t\"type\": \"function\"\r\n"
            + "\t}\r\n"
            + "]";

    public static final String FUNC_ADDADEPT = "addAdept";

    public static final String FUNC_ADDANDFUNDCULTIST = "addAndFundCultist";

    public static final String FUNC_ADDCULTIST = "addCultist";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_DESTROY = "destroy";

    public static final String FUNC_GETCREATIONTIMESTAMPOFADEPT = "getCreationTimestampOfAdept";

    public static final String FUNC_GETCREATIONTIMESTAMPOFCULTIST = "getCreationTimestampOfCultist";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_REMOVEADEPT = "removeAdept";

    public static final String FUNC_REMOVECULTIST = "removeCultist";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event APPROVAL_EVENT = new Event("Approval",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected CultOfTheCryptomancerContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CultOfTheCryptomancerContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected CultOfTheCryptomancerContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected CultOfTheCryptomancerContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public static List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public static List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addAdept(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDADEPT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addAndFundCultist(String account, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDANDFUNDCULTIST,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account),
                        new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> addCultist(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDCULTIST,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner),
                        new org.web3j.abi.datatypes.Address(160, spender)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender),
                        new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DECIMALS,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseAllowance(String spender, BigInteger subtractedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DECREASEALLOWANCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender),
                        new org.web3j.abi.datatypes.generated.Uint256(subtractedValue)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> destroy() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DESTROY,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> getCreationTimestampOfAdept(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCREATIONTIMESTAMPOFADEPT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getCreationTimestampOfCultist(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETCREATIONTIMESTAMPOFCULTIST,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender, BigInteger addedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INCREASEALLOWANCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender),
                        new org.web3j.abi.datatypes.generated.Uint256(addedValue)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(String account, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account),
                        new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> removeAdept(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVEADEPT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> removeCultist(String account) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REMOVECULTIST,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, account)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String to, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to),
                        new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from),
                        new org.web3j.abi.datatypes.Address(160, to),
                        new org.web3j.abi.datatypes.generated.Uint256(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static CultOfTheCryptomancerContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CultOfTheCryptomancerContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CultOfTheCryptomancerContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CultOfTheCryptomancerContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static CultOfTheCryptomancerContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new CultOfTheCryptomancerContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static CultOfTheCryptomancerContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CultOfTheCryptomancerContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<CultOfTheCryptomancerContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CultOfTheCryptomancerContract.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CultOfTheCryptomancerContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CultOfTheCryptomancerContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<CultOfTheCryptomancerContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(CultOfTheCryptomancerContract.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<CultOfTheCryptomancerContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CultOfTheCryptomancerContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }
}
