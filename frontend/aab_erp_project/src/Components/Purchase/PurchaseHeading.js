import React, { useState, useEffect, useCallback } from "react";
import PurchaseOrder from './PurchaseOrder';
import PurchaseHistory from "./PurchaseHistory";
import PurchaseInputData from "./PurchaseInputData";

const PurchaseHeading = () => {
    const [activeTab, setActiveTab] = useState(() => {
        return localStorage.getItem('activeTab') || 'purchaseorder';
    });

    useEffect(() => {
        // Prevent infinite re-renders by checking if the value actually changed
        const storedTab = localStorage.getItem('activeTab');
        if (storedTab !== activeTab) {
            localStorage.setItem('activeTab', activeTab);
        }
    }, [activeTab]);

    const renderContent = () => {
        switch (activeTab) {
            case 'purchaseorder':
                return <PurchaseOrder />;
            case 'purchasehistory':
                return <PurchaseHistory />;
            case 'purchaseinputdata':
                return <PurchaseInputData />;
            default:
                return <PurchaseOrder />;
        }
    };

    return (
        <div className="bg-[#FAF6ED]">
            <div className="topbar-title">
                <h2
                    className={`link ${activeTab === 'purchaseorder' ? 'active' : ''}`}
                    onClick={() => setActiveTab('purchaseorder')}
                >
                    Purchase Order
                </h2>
                <h2
                    className={`link ${activeTab === 'purchasehistory' ? 'active' : ''}`}
                    onClick={() => setActiveTab('purchasehistory')}
                >
                    History
                </h2>
                <h2
                    className={`link ${activeTab === 'purchaseinputdata' ? 'active' : ''}`}
                    onClick={() => setActiveTab('purchaseinputdata')}
                >
                    Input Data
                </h2>
            </div>
            <div className="content">
                {renderContent()}
            </div>
        </div>
    );
}

export default PurchaseHeading
